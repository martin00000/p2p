package com.eloan.base.mapper;

import com.eloan.base.domain.Logininfo;
import java.util.List;

public interface LogininfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table logininfo
     *
     * @mbggenerated Sun Dec 25 02:47:10 CST 2016
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table logininfo
     *
     * @mbggenerated Sun Dec 25 02:47:10 CST 2016
     */
    int insert(Logininfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table logininfo
     *
     * @mbggenerated Sun Dec 25 02:47:10 CST 2016
     */
    Logininfo selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table logininfo
     *
     * @mbggenerated Sun Dec 25 02:47:10 CST 2016
     */
    List<Logininfo> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table logininfo
     *
     * @mbggenerated Sun Dec 25 02:47:10 CST 2016
     */
    int updateByPrimaryKey(Logininfo record);
}