package com.example.pttmobile4.api;

import com.example.pttmobile4.models.Project;
import com.example.pttmobile4.models.Report;
import com.example.pttmobile4.models.Session;
import com.example.pttmobile4.models.User;

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

    @POST("users")
    @Headers("Content-Type: application/json")
    Call<User> createUser(@Body RequestBody body);

    @PUT("users/{userId}")
    @Headers("Content-Type: application/json")
    Call<User> editUser(@Path("userId") int id, @Body RequestBody body);

    @GET("users/{userId}")
    @Headers("Content-Type: application/json")
    Call<User> getUser(@Path("userId") int id);

    @GET("users")
    @Headers("Content-Type: application/json")
    Call<User> getUsers();

    @DELETE("users/{userId}")
    @Headers("Content-Type: application/json")
    Call<User> deleteUser(@Path("userId") int id);


    @POST("users/{userId}/projects")
    @Headers("Content-Type: application/json")
    Call<Project> createProject(@Path("userId") int id, @Body RequestBody body);

    @GET("users/{userId}/projects")
    @Headers("Content-Type: application/json")
    Call<Project> getProjects(@Path("userId") int id);

    @GET("users/{userId}/projects/{projectId}")
    @Headers("Content-Type: application/json")
    Call<Project> getProject(@Path("userId") int userId, @Path("projectId") int projectId);

    @PUT("users/{userId}/projects/{projectId}")
    @Headers("Content-Type: application/json")
    Call<Project> updateProject(@Path("userId") int userId, @Path("projectId") int projectId, @Body RequestBody body);

    @DELETE("users/{userId}/projects/{projectId}")
    @Headers("Content-Type: application/json")
    Call<Project> deleteProject(@Path("userId") int userId, @Path("projectId") int projectId);

    @POST("users/{userId}/projects/{projectId}/sessions")
    @Headers("Content-Type: application/json")
    Call<Session> createSession(@Path("userId") int userId, @Path("projectId") int projectId, @Body RequestBody body);

    @PUT("users/{userId}/projects/{projectId}/sessions/{sessionId}")
    @Headers("Content-Type: application/json")
    Call<Session> updateSession(@Path("userId") int userId, @Path("projectId") int projectId, @Path("sessionId") int sessionId, @Body RequestBody body);

    //TODO: When backend is done, double check
    @GET("users/{userId}/projects/{projectId}/report")
    @Headers("Content-Type: application/json")
    Call<Report> getReport(@Path("userId") int userId, @Path("projectId") int projectId, @Query("from") String from, @Query("to") String to, @Query("includeCompletedPomodoros") boolean includeCompletedPomodoros, @Query("includeTotalHoursWorkedOnProject") boolean includeTotalHoursWorkedOnProject);

}
