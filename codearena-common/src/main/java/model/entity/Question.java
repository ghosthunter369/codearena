package model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;

import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 题目
 * @TableName question
 */
@Getter
@TableName(value ="question")
@Data
@Document(indexName = "question")
public class Question implements Serializable {
    /**
     * id
     * -- GETTER --
     *  id

     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 标题
     * -- GETTER --
     *  标题

     */
    private String title;

    /**
     * 内容
     * -- GETTER --
     *  内容

     */
    private String content;

    /**
     * 标签列表（json 数组）
     * -- GETTER --
     *  标签列表（json 数组）

     */
    private String tags;

    /**
     * 推荐答案
     * -- GETTER --
     *  推荐答案

     */
    private String answer;

    /**
     * 创建用户 id
     * -- GETTER --
     *  创建用户 id

     */
    private Long userId;

    /**
     * 编辑时间
     * -- GETTER --
     *  编辑时间

     */
    private Date editTime;

    /**
     * 创建时间
     * -- GETTER --
     *  创建时间

     */
    private Date createTime;

    /**
     * 更新时间
     * -- GETTER --
     *  更新时间

     */
    private Date updateTime;

    /**
     * 是否删除
     * -- GETTER --
     *  是否删除

     */
    private Integer isDelete;

    /**
     * 题目提交数
     * -- GETTER --
     *  题目提交数

     */
    private Integer submitNum;

    /**
     * 题目通过数
     * -- GETTER --
     *  题目通过数

     */
    private Integer acceptedNum;

    /**
     * 判题用例（json 数组）
     * -- GETTER --
     *  判题用例（json 数组）

     */
    private String judgeCase;

    /**
     * 判题配置（json 对象）
     * -- GETTER --
     *  判题配置（json 对象）

     */
    private String judgeConfig;

    /**
     * 题目类型（0-面试题，1-算法题）
     * -- GETTER --
     *  题目类型（0-面试题，1-算法题）

     */
    private Integer questionType;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}