package com.eloan.business.mapper;

import com.eloan.business.domain.Userfile;
import java.util.List;

public interface UserfileMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userfile
     *
     * @mbggenerated Wed Jan 04 17:47:01 CST 2017
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userfile
     *
     * @mbggenerated Wed Jan 04 17:47:01 CST 2017
     */
    int insert(Userfile record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userfile
     *
     * @mbggenerated Wed Jan 04 17:47:01 CST 2017
     */
    Userfile selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userfile
     *
     * @mbggenerated Wed Jan 04 17:47:01 CST 2017
     */
    List<Userfile> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userfile
     *
     * @mbggenerated Wed Jan 04 17:47:01 CST 2017
     */
    int updateByPrimaryKey(Userfile record);
}