package com.eloan.base.mapper;

import com.eloan.base.domain.SystemDictionary;

import java.util.List;

public interface SystemdictionaryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SystemDictionary
     *
     * @mbggenerated Tue Dec 27 17:57:22 CST 2016
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SystemDictionary
     *
     * @mbggenerated Tue Dec 27 17:57:22 CST 2016
     */
    int insert(SystemDictionary record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SystemDictionary
     *
     * @mbggenerated Tue Dec 27 17:57:22 CST 2016
     */
    SystemDictionary selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SystemDictionary
     *
     * @mbggenerated Tue Dec 27 17:57:22 CST 2016
     */
    List<SystemDictionary> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SystemDictionary
     *
     * @mbggenerated Tue Dec 27 17:57:22 CST 2016
     */
    int updateByPrimaryKey(SystemDictionary record);
}