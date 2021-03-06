package com.example.pttmobile4.api;

import com.example.pttmobile4.models.Project;
import com.example.pttmobile4.models.Report;
import com.example.pttmobile4.models.Session;
import com.example.pttmobile4.models.User;

import java.util.ArrayList;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {

    // USERS

    @POST("users")
    @Headers("Content-Type: application/json")
    Call<User> createUser(@Body RequestBody body);

    @PUT("users/{userId}")
    @Headers("Content-Type: application/json")
    Call<User> editUser(@Path("userId") int userId, @Body RequestBody body);

    @GET("users/{userId}")
    @Headers("Content-Type: application/json")
    Call<User> getUser(@Path("userId") int userId);

    @GET("users")
    @Headers("Content-Type: application/json")
    Call<ArrayList<User>> getUsers();

    @DELETE("users/{userId}")
    @Headers("Content-Type: application/json")
    Call<User> deleteUser(@Path("userId") int userId);

    // PROJECTS

    @POST("users/{userId}/projects")
    @Headers("Content-Type: application/json")
    Call<Project> createProject(@Path("userId") int userId, @Body RequestBody body);

    @GET("users/{userId}/projects")
    @Headers("Content-Type: application/json")
    Call<ArrayList<Project>> getProjects(@Path("userId") int userId);

    @GET("users/{userId}/projects/{projectId}")
    @Headers("Content-Type: application/json")
    Call<Project> getProject(@Path("userId") int userId, @Path("projectId") int projectId);

    @PUT("users/{userId}/projects/{projectId}")
    @Headers("Content-Type: application/json")
    Call<Project> updateProject(@Path("userId") int userId, @Path("projectId") int projectId, @Body RequestBody body);

    @DELETE("users/{userId}/projects/{projectId}")
    @Headers("Content-Type: application/json")
    Call<Project> deleteProject(@Path("userId") int userId, @Path("projectId") int projectId);

    // SESSIONS

    @POST("users/{userId}/projects/{projectId}/sessions")
    @Headers("Content-Type: application/json")
    Call<Session> createSession(@Path("userId") int userId, @Path("projectId") int projectId, @Body RequestBody body);

    @PUT("users/{userId}/projects/{projectId}/sessions/{sessionId}")
    @Headers("Content-Type: application/json")
    Call<Session> updateSession(@Path("userId") int userId, @Path("projectId") int projectId,
                                @Path("sessionId") int sessionId, @Body RequestBody body);

    // REPORT
    @GET("users/{userId}/projects/{projectId}/report")
    @Headers("Content-Type: application/json")
    Call<Report> getReport(@Path("userId") int userId, @Path("projectId") int projectId, @Query("from") String from, @Query("to") String to,
                           @Query("includeCompletedPomodoros") boolean includeCompletedPomodoros, @Query("includeTotalHoursWorkedOnProject") boolean includeTotalHoursWorkedOnProject);

}
