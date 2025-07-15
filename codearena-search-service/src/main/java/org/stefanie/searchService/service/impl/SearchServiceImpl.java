package org.stefanie.searchService.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import model.dto.question.QuestionQueryRequest;
import model.entity.Post;
import model.entity.Question;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.stefanie.searchService.mapper.QuestionRepository;
import org.stefanie.searchService.mapper.SearchMapper;
import org.stefanie.searchService.service.SearchService;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 帖子服务实现
 */
@Service
@Slf4j
public class SearchServiceImpl extends ServiceImpl<SearchMapper, Post> implements SearchService {

    @Resource
    private QuestionRepository questionRepository;

    @Resource
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    public Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }


    @Override
    public List<Question> searchQuestion(QuestionQueryRequest question) {
        String title = question.getTitle();
        String content = question.getContent();
        String tags = question.getTags();

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        // ========= 模糊或精确匹配 title、content =========
        boolean hasShouldClause = false;

        if (StringUtils.isNotBlank(title)) {
            // title 模糊匹配，权重 2
            boolQueryBuilder.should(QueryBuilders.matchQuery("title", title).boost(2));
            // title 精确匹配（keyword），权重更高
            boolQueryBuilder.should(QueryBuilders.termQuery("title.keyword", title).boost(3));
            hasShouldClause = true;
        }

        if (StringUtils.isNotBlank(content)) {
            boolQueryBuilder.should(QueryBuilders.matchQuery("content", content));
            hasShouldClause = true;
        }

        // 如果存在 should 条件，最少命中一个
        if (hasShouldClause) {
            boolQueryBuilder.minimumShouldMatch(1);
        }

        // ========= tags 精确过滤 =========
        if (StringUtils.isNotBlank(tags)) {
            List<String> tagList = Arrays.stream(tags.split(","))
                    .map(String::trim)
                    .filter(StringUtils::isNotBlank)
                    .collect(Collectors.toList());
            if (StringUtils.isNotBlank(tags)) {
                boolQueryBuilder.filter(QueryBuilders.wildcardQuery("tags.keyword", "*" + tags + "*"));
            }
        }

        // ========= 构建查询：添加分页支持 =========
        int pageNum = Math.max(1, question.getCurrent());
        int pageSize = Math.min(100, Math.max(1, question.getPageSize()));

        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(boolQueryBuilder)
                .withPageable(PageRequest.of(pageNum - 1, pageSize))
                .build();

        // ========= 执行查询 =========
        try {
            SearchHits<Question> searchHits = elasticsearchRestTemplate.search(searchQuery, Question.class);
            return searchHits.getSearchHits().stream()
                    .map(SearchHit::getContent)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Search failed: " + e.getMessage(), e);
        }
    }

}




