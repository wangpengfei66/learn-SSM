package com.kaishengit.entity;

public class Movie {
    private Integer id;

    private Integer rank;

    private String name;

    private Double score;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

	@Override
	public String toString() {
		return "Movie [id=" + id + ", rank=" + rank + ", name=" + name + ", score=" + score + "]";
	}
    
    
}