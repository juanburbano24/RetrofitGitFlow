package pe.edu.cibertec.retrofitgitflow.domain.post_detail_interactor;

import java.util.List;

import pe.edu.cibertec.retrofitgitflow.data.entities.Post;
import pe.edu.cibertec.retrofitgitflow.domain.mainInteractor.IMainInteractor;
import pe.edu.cibertec.retrofitgitflow.network.ApiClient;
import pe.edu.cibertec.retrofitgitflow.network.JsonPlaceHolderApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostDetailInteractorImpl implements IPostDetailInteractor{

    @Override
    public void getPost(int postId, IPostDetailCallBack postDetailCallBack) {
        JsonPlaceHolderApi jsonPlaceHolderApi = ApiClient.getClient().create(JsonPlaceHolderApi.class);
        Call<Post> call = jsonPlaceHolderApi.getPost(postId);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(!response.isSuccessful()){
                    postDetailCallBack.onError("Code: " + response.code());
                } else{
                    postDetailCallBack.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                postDetailCallBack.onError("Failure: " + t.getMessage());
            }
        });
    }
}
