package com.Caoxin.dao;

import com.Caoxin.model.Product;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao implements  IProductDao{
    @Override
    public  int save(Product product, Connection con) throws SQLException {
        int n = 0;
        String sql = "insert into Product(productName,productDescription,picture,price,categoryId) values(?,?,?,?,?)";
        PreparedStatement pt = con.prepareStatement(sql);
        pt.setString(1, product.getProductName());
        pt.setString(2, product.getProductDescription());
        if(product.getPicture()!=null) {
            //for sql server
            pt.setBinaryStream(3, product.getPicture());
        }
        pt.setDouble(4, product.getPrice());
        pt.setInt(5, product.getCategoryId());
        n = pt.executeUpdate();
        if (n > 0) {
            return n;
        }
        return 0;
    }//end save

    @Override
    public int delete(Integer productId, Connection con) throws SQLException {
        String sql = "DELETE FROM Product WHERE productId=?;";
        PreparedStatement pt = con.prepareStatement(sql);
        pt.setInt(1, productId);
        int rs = pt.executeUpdate();
        return rs;
    }

    @Override
    public int update(Product instance, Connection con) throws SQLException {
        int n = 0;
        String sql = "UPDATE Product SET productName=?,productDescription=?,picture=?,price=?,categoryId=? WHERE ProductId=?;";
        PreparedStatement pt = con.prepareStatement(sql);
        pt.setString(1, instance.getProductName());
        pt.setString(2, instance.getProductDescription());
        if (instance.getPicture() != null) {
            pt.setBlob(3, instance.getPicture());
        }
        pt.setDouble(4, instance.getPrice());
        pt.setInt(5, instance.getCategoryId());
        pt.setInt(6, instance.getProductId());
        n = pt.executeUpdate();
        if (n > 0) {
            return n;
        }
        return 0;
    }
    @Override
    public Product findById(Integer productId, Connection con) throws SQLException {
        String sql = "select * from Product where productId=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, productId);
        ResultSet rs = ps.executeQuery();
        Product product = new Product();
        if (rs.next()) {
            product.setProductId(rs.getInt("productId"));
            product.setProductName(rs.getString("productName"));
            product.setProductDescription(rs.getString("productDescription"));
            product.setPicture((InputStream) rs.getBlob("picture"));
            product.setPrice(rs.getDouble("price"));
            product.setCategoryId(rs.getInt("categoryId"));
        }
        return product;
    }

    @Override
    public List<Product> findByCategoryId(int categoryId, Connection con) throws SQLException {
        List<Product> productList = new ArrayList<Product>();
        String sql = "select * from Product where categoryId=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, categoryId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Product product = new Product();
            product.setProductId(rs.getInt("productId"));
            product.setProductName(rs.getString("productName"));
            product.setProductDescription(rs.getString("productDescription"));
            product.setPicture((InputStream) rs.getBlob("picture"));
            product.setPrice(rs.getDouble("price"));
            product.setCategoryId(rs.getInt("categoryId"));
            productList.add(product);
        }
        return productList;
    }

    @Override
    public List<Product> findByPrice(double minPrice, double maxPrice, Connection con) throws SQLException {
        List<Product> productList = new ArrayList<Product>();
        String sql = "select * from Product where price between ? and ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setDouble(1, minPrice);
        ps.setDouble(2, maxPrice);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Product product = new Product();
            product.setProductId(rs.getInt("productId"));
            product.setProductName(rs.getString("productName"));
            product.setProductDescription(rs.getString("productDescription"));
            product.setPicture((InputStream) rs.getBlob("picture"));
            product.setPrice(rs.getDouble("price"));
            product.setCategoryId(rs.getInt("categoryId"));
            productList.add(product);
        }
        return productList;
    }

    @Override
    public List<Product> findAll(Connection con) throws SQLException {
        List<Product> productList = new ArrayList<Product>();
        String sql = "select * from Product";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Product product = new Product();
            product.setProductId(rs.getInt("productId"));
            product.setProductName(rs.getString("productName"));
            product.setProductDescription(rs.getString("productDescription"));
            product.setPicture((InputStream) rs.getBlob("picture"));
            product.setPrice(rs.getDouble("price"));
            product.setCategoryId(rs.getInt("categoryId"));
            productList.add(product);
        }
        return productList;
    }

    @Override
    public List<Product> findByProductName(String productName, Connection con) throws SQLException {
        List<Product> productList = new ArrayList<Product>();
        String sql = "select * from Product where productName=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, productName);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Product product = new Product();
            product.setProductId(rs.getInt("productId"));
            product.setProductName(rs.getString("productName"));
            product.setProductDescription(rs.getString("productDescription"));
            product.setPicture((InputStream) rs.getBlob("picture"));
            product.setPrice(rs.getDouble("price"));
            product.setCategoryId(rs.getInt("categoryId"));
            productList.add(product);
        }
        return productList;
    }

    @Override
    public List<Product> getPicture(Integer productId, Connection con) throws SQLException {
        List<Product> productList = new ArrayList<Product>();
        String sql = "select * from Product where productId=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, productId);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Product product = new Product();
            if (rs.getBlob("picture") != null)
                product.setPicture((InputStream) rs.getBlob("picture"));
            productList.add(product);
        }
        return productList;
    }


    public byte[] getPictureById(int productId, Connection con) throws SQLException {
        byte[] imgByte = null;
        String sql = "select picture from Product where productId=?";
        PreparedStatement pt = con.prepareStatement(sql);
        pt.setInt(1, productId);
        ResultSet rs = pt.executeQuery();
        while (rs.next()) {
            Blob blob = rs.getBlob("picture");
            imgByte = ((Blob) blob).getBytes(1, (int) blob.length());
        }
        return imgByte;
    }
}