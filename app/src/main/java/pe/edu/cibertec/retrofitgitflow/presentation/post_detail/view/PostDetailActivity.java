package pe.edu.cibertec.retrofitgitflow.presentation.post_detail.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import pe.edu.cibertec.retrofitgitflow.R;
import pe.edu.cibertec.retrofitgitflow.data.entities.Post;
import pe.edu.cibertec.retrofitgitflow.domain.post_detail_interactor.PostDetailInteractorImpl;
import pe.edu.cibertec.retrofitgitflow.presentation.main.IMainContract;
import pe.edu.cibertec.retrofitgitflow.presentation.post_detail.IPostDetailContract;
import pe.edu.cibertec.retrofitgitflow.presentation.post_detail.presenter.PostDetailPresenter;

public class PostDetailActivity extends AppCompatActivity implements IPostDetailContract.IView {

    PostDetailPresenter presenter;
    private TextView textViewResultPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        textViewResultPost = findViewById(R.id.textViewResultPost);
        int id = getIntent().getIntExtra("post_id", -1);
        if(id == -1){
            showError("No nos llegó el post_id");
            finish();
        }
        presenter = new PostDetailPresenter(new PostDetailInteractorImpl());
        presenter.attachView(this);
        presenter.getPost(id);
    }


    @Override
    public void showError(String errorMsg) {

    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void getPostSuccess(Post post) {
        System.out.println(post.getTitle());
        textViewResultPost.setText(post.getTitle());
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }

    @Override
    public void onDetachedFromWindow() {
        presenter.detachView();
        super.onDetachedFromWindow();
    }
}
