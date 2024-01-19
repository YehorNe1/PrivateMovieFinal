package dal;

import be.Category;
import be.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseData {

    public List<Category> getAllDataOfCategories() {
        List<Category> categories = new ArrayList<>();

        try (Connection connection = new DatabaseConnection().getConnection()) {
            String sql = "SELECT * FROM CategoryTable";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String name = resultSet.getString("name");

                        Category category = new Category(id, name);
                        categories.add(category);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    public List<Movie> getAllDataOfMovies(int categoryId) {
        List<Movie> movies = new ArrayList<>();

        try (Connection connection = new DatabaseConnection().getConnection()) {
            String sql = "SELECT MovieTable.* FROM MovieTable " +
                    "JOIN CategoryMovieTable ON MovieTable.id = CategoryMovieTable.movieId " +
                    "WHERE CategoryMovieTable.categoryId = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, categoryId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String title = resultSet.getString("title");
                        double rating = resultSet.getDouble("rating");
                        String fileLink = resultSet.getString("fileLink");
                        double personalRating = resultSet.getDouble("personalRating");

                        Movie movie = new Movie(id, title, rating, fileLink, personalRating);
                        movies.add(movie);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }

    public Category getCategoryByName(String categoryName) {
        Category category = null;

        try (Connection connection = new DatabaseConnection().getConnection()) {
            String sql = "SELECT * FROM CategoryTable WHERE name = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, categoryName);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String name = resultSet.getString("name");

                        category = new Category(id, name);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return category;
    }

    public List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<>();

        try (Connection connection = new DatabaseConnection().getConnection()) {
            String query = "SELECT * FROM MovieTable";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String title = resultSet.getString("title");
                        double rating = resultSet.getDouble("rating");
                        String fileLink = resultSet.getString("fileLink");
                        double personalRating = resultSet.getDouble("personalRating");

                        Movie movie = new Movie(id, title, rating, fileLink, personalRating);
                        movies.add(movie);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }
}
