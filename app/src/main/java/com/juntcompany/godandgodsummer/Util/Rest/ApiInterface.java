package com.juntcompany.godandgodsummer.Util.Rest;

import com.juntcompany.godandgodsummer.Data.Chat;
import com.juntcompany.godandgodsummer.Data.Timeline;
import com.juntcompany.godandgodsummer.Data.User;
import com.juntcompany.godandgodsummer.DataStructure.ActivityResult;
import com.juntcompany.godandgodsummer.DataStructure.FriendResult;
import com.juntcompany.godandgodsummer.DataStructure.LikeResult;
import com.juntcompany.godandgodsummer.DataStructure.ReplyResult;
import com.juntcompany.godandgodsummer.DataStructure.ReportResult;
import com.juntcompany.godandgodsummer.DataStructure.TimeLine.TimelineResultResponse;
import com.juntcompany.godandgodsummer.DataStructure.UserInfoResponse;
import com.juntcompany.godandgodsummer.DataStructure.TimeLine.WriteResult;
import com.juntcompany.godandgodsummer.DataStructure.UserSearchResponse;
import com.juntcompany.godandgodsummer.DataStructure.UserTimelineResponse;
import com.juntcompany.godandgodsummer.DataStructure.YoutubeResult;

import java.io.File;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by EOM on 2016-08-02.
 */
public interface ApiInterface {

    @FormUrlEncoded //O
    @POST("/auth/signup") //회원가입 api // email, name, password,religion, phone,gender,birth
    Call<User> createUser(@Field("email") String email, @Field("name") String name, @Field("password") String password, @Field("religion") String religion, @Field("phone") String phone, @Field("gender") String gender, @Field("birth") String birth, @Field("token") String token);

    @FormUrlEncoded //O
    @POST("/auth/login") //로그인 api
    Call<User> userLogin(@Field("email") String email, @Field("password") String password);

    @GET("/auth/logout") //O
    Call<User> userLogout();

    @FormUrlEncoded //O
    @POST("/auth/regid") //토큰등록 api
    Call<User> regId(@Field("email") String email, @Field("token") String token);

    @FormUrlEncoded //O
    @POST("/chat/invite") //O
    Call<Chat> inviteFriend(@Field("room_number") String room_number, @Field("id") String id);

    @GET("/user/myinfo/{email}")
    Call<UserInfoResponse> userInfo(@Path("email") String email);

    @Multipart
    @POST("/user/changePhoto/{user_id}")
    Call<UserInfoResponse> userChangePhoto(@Path("user_id") String userId, @Part("photo") String userPhoto);

//    @GET("/board/write/{username}/{email}/{content}")
//    Call<WriteResult> writeTimeLine(@Path("username") String username, @Path("email") String email, @Path("content") String content);

    @GET("/user/search/{email}")//O
    Call<UserSearchResponse> userSearch(@Path("email") String email);

    @Multipart //O
    @POST("/board/write")
    Call<WriteResult> writeTimeLine(@Part("username") String username, @Part("email") String email, @Part("userPhoto") String userPhoto, @Part("content") String content, @Part("photo") File photo);


    @Multipart
    @POST("/board/write/edit/{board_id}")
    Call<Timeline> writeEditTimeLine(@Path("board_id") String boardId, @Part("content") String content, @Part("photo") String photo);

    @FormUrlEncoded
    @POST("/board/delete")
    Call<WriteResult> deleteTimeLine(@Field("board_id") String boardId);


    @GET("/board/showAll/{my_user_id}")//O
    Call<TimelineResultResponse> getTimeLine(@Path("my_user_id") int myUserId, @Query("skip") int skip);

    @GET("/board/show/{email}") //O
    Call<UserTimelineResponse> getUserTimeline(@Path("email") String email);

    @FormUrlEncoded
    @POST("/board/reply/write")
    Call<WriteResult> writeReply(@Field("id") String boardId, @Field("username") String username, @Field("userEmail") String userEmail, @Field("content") String content, @Field("userPhoto") String userPhoto);

    @FormUrlEncoded
    @POST("/board/reply/write/edit")
    Call<ReplyResult> editReply(@Field("reply_id") String replyId, @Field("content") String content);

    @FormUrlEncoded
    @POST("/board/reply/delete")
    Call<ReplyResult> deleteReply(@Field("reply_id") String replyId);

    @FormUrlEncoded
    @POST("/board/like")
    Call<LikeResult> likeTimeLine(@Field("board_id") String boardId, @Field("name") String name, @Field("email") String email);

    @FormUrlEncoded
    @POST("/board/like/delete")
    Call<LikeResult> deleteLike(@Field("like_id") String likeId);

    @FormUrlEncoded
    @POST("/report/board")
    Call<ReportResult> reportBoard(@Field("username") String username, @Field("email") String email, @Field("content") String content, @Field("photo") String photo, @Field("board_id") String boardId);

    @FormUrlEncoded
    @POST("/friend/request")
    Call<FriendResult> friendRequest(@Field("my_user_id") int myUserId, @Field("other_user_id") int otherUserId);

    @FormUrlEncoded
    @POST("/friend/accept")
    Call<FriendResult> friendAccept(@Field("my_user_id") int myUserId, @Field("other_user_id") int otherUserId);

    @FormUrlEncoded
    @POST("/friend/decline")
    Call<FriendResult> friendDecline(@Field("my_user_id") int myUserId, @Field("other_user_id") int otherUserId);

    @GET("/friend/list/{my_user_id}")
    Call<FriendResult> friendList(@Path("my_user_id") int myUserId);

    @GET("/friend/pending/{my_user_id}")
    Call<FriendResult> friendPendingList(@Path("my_user_id") int myUserId);

    @GET("/friend/relation/{my_user_id}/{other_user_id}")
    Call<FriendResult> friendRelation(@Path("my_user_id") int myUserId, @Path("other_user_id") int otherUserId);

    @FormUrlEncoded
    @POST("/friend/block")
    Call<FriendResult> friendBlock(@Field("my_user_id") int myUserId, @Field("other_user_id") int otherUserId);

    @GET("/activity/show/{email}")
    Call<ActivityResult> showActivity(@Path("email") String email);

    @GET("/youtube/show")
    Call<YoutubeResult> showYoutube();

};
