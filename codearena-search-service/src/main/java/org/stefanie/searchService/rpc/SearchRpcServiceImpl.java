package org.stefanie.searchService.rpc;

import model.dto.question.QuestionQueryRequest;
import model.entity.Question;
import org.apache.dubbo.config.annotation.DubboService;
import org.stefanie.searchService.service.SearchService;
import org.stefanie.serviceClient.SearchRpcService;

import javax.annotation.Resource;
import java.util.List;

@DubboService(group = "dubbo-group")
public class SearchRpcServiceImpl implements SearchRpcService {

    @Resource
    private SearchService searchService;

    @Override
    public List<Question> searchQuestion(QuestionQueryRequest questionQueryRequest) {
        return searchService.searchQuestion(questionQueryRequest);
    }
}
