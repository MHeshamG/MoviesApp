package com.example.mheshamg.xmovies.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MovieDetails {

    @SerializedName("adult")
    private boolean adult;
    @SerializedName("backdrop_path")
    private String backdropPath;
    @SerializedName("belongs_to_collection")
    private String belongsToCollection = null;
    @SerializedName("budget")
    private float budget;
    @SerializedName("genres")
    ArrayList< Genre > genres = new ArrayList();
    @SerializedName("homepage")
    private String homepage = null;
    @SerializedName("id")
    private Long id;
    @SerializedName("imdb_id")
    private String imdbId;
    @SerializedName("original_language")
    private String originalLanguage;
    @SerializedName("original_title")
    private String originalTitle;
    @SerializedName("overview")
    private String overview;
    @SerializedName("popularity")
    private float popularity;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("production_companies")
    ArrayList < ProductionCompany > productionCompanies = new ArrayList();
    @SerializedName("production_countries")
    ArrayList < ProductionCountries > productionCountries = new ArrayList();
    @SerializedName("releaseDate")
    private String releaseDate;
    @SerializedName("revenue")
    private float revenue;
    @SerializedName("runtime")
    private float runtime;
    @SerializedName("spoken_langauges")
    ArrayList < SpokenLanguage > spokenLangauges = new ArrayList();
    @SerializedName("status")
    private String status;
    @SerializedName("tagline")
    private String tagline;
    @SerializedName("title")
    private String title;
    @SerializedName("video")
    private boolean video;
    @SerializedName("vote_average")
    private float voteAverage;
    @SerializedName("vote_count")
    private float vote_count;


    // Getter Methods

    public boolean getAdult() {
        return adult;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public String getBelongsToCollection() {
        return belongsToCollection;
    }

    public float getBudget() {
        return budget;
    }

    public String getHomepage() {
        return homepage;
    }

    public Long getId() {
        return id;
    }

    public String getImdbId() {
        return imdbId;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public float getPopularity() {
        return popularity;
    }

    public String getposterPath() {
        return posterPath;
    }

    public String getreleaseDate() {
        return releaseDate;
    }

    public float getRevenue() {
        return revenue;
    }

    public float getRuntime() {
        return runtime;
    }

    public String getStatus() {
        return status;
    }

    public String getTagline() {
        return tagline;
    }

    public String getTitle() {
        return title;
    }

    public boolean getVideo() {
        return video;
    }

    public float getVote_average() {
        return voteAverage;
    }

    public float getVote_count() {
        return vote_count;
    }

    // Setter Methods

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public void setBackdrop_path(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public void setBelongsToCollection(String belongsToCollection) {
        this.belongsToCollection = belongsToCollection;
    }

    public void setBudget(float budget) {
        this.budget = budget;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setRevenue(float revenue) {
        this.revenue = revenue;
    }

    public void setRuntime(float runtime) {
        this.runtime = runtime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public void setVote_average(float vote_average) {
        this.voteAverage = vote_average;
    }

    public void setVote_count(float vote_count) {
        this.vote_count = vote_count;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public ArrayList<Genre> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<Genre> genres) {
        this.genres = genres;
    }
    public ArrayList<ProductionCompany> getProductionCompanies() {
        return productionCompanies;
    }

    public void setProductionCompanies(ArrayList<ProductionCompany> productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    public ArrayList<ProductionCountries> getProductionCountries() {
        return productionCountries;
    }

    public void setProductionCountries(ArrayList<ProductionCountries> productionCountries) {
        this.productionCountries = productionCountries;
    }

    public ArrayList<SpokenLanguage> getSpokenLangauges() {
        return spokenLangauges;
    }

    public void setSpokenLangauges(ArrayList<SpokenLanguage> spokenLangauges) {
        this.spokenLangauges = spokenLangauges;
    }

    public boolean isVideo() {
        return video;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(float voteAverage) {
        this.voteAverage = voteAverage;
    }
}