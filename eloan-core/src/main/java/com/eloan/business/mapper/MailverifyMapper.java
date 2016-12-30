package com.eloan.business.mapper;

import com.eloan.business.domain.Mailverify;
import java.util.List;

public interface MailverifyMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mailverify
     *
     * @mbggenerated Fri Dec 30 16:31:58 CST 2016
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mailverify
     *
     * @mbggenerated Fri Dec 30 16:31:58 CST 2016
     */
    int insert(Mailverify record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mailverify
     *
     * @mbggenerated Fri Dec 30 16:31:58 CST 2016
     */
    Mailverify selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mailverify
     *
     * @mbggenerated Fri Dec 30 16:31:58 CST 2016
     */
    List<Mailverify> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mailverify
     *
     * @mbggenerated Fri Dec 30 16:31:58 CST 2016
     */
    int updateByPrimaryKey(Mailverify record);

    /**
     * 根据randCode查询邮件信息
     * @param randomCode
     * @return
     */
    Mailverify selectByCheckCode(String randomCode);
}