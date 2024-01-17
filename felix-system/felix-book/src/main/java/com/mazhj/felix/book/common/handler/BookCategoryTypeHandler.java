package com.mazhj.felix.book.common.handler;

import com.mazhj.common.pojo.enums.BookCategory;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author mazhj
 */
@MappedTypes(value = {BookCategory.class})
public class BookCategoryTypeHandler extends BaseTypeHandler<BookCategory> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, BookCategory parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.name());
    }

    @Override
    public BookCategory getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String name = rs.getString(columnName);
        return name == null ? null : BookCategory.valueOf(name.toUpperCase());
    }

    @Override
    public BookCategory getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String name = rs.getString(columnIndex);
        return name == null ? null : BookCategory.valueOf(name.toUpperCase());
    }

    @Override
    public BookCategory getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String name = cs.getString(columnIndex);
        return name == null ? null : BookCategory.valueOf(name.toUpperCase());
    }
}

