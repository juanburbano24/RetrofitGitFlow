package pe.edu.cibertec.retrofitgitflow.presentation.post_detail.presenter;

import java.util.List;

import pe.edu.cibertec.retrofitgitflow.data.entities.Post;
import pe.edu.cibertec.retrofitgitflow.domain.mainInteractor.IMainInteractor;
import pe.edu.cibertec.retrofitgitflow.domain.post_detail_interactor.IPostDetailInteractor;
import pe.edu.cibertec.retrofitgitflow.presentation.main.IMainContract;
import pe.edu.cibertec.retrofitgitflow.presentation.post_detail.IPostDetailContract;

public class PostDetailPresenter implements IPostDetailContract.IPresenter {
    IPostDetailContract.IView view;
    IPostDetailInteractor interactor;

    public PostDetailPresenter(IPostDetailInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void attachView(IPostDetailContract.IView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;

    }

    @Override
    public boolean isViewAttached() {
        return view != null;
    }

    @Override
    public void getPost(int postId) {
        view.showProgressBar();
        //Aca usaremos un interceptor de la clase Main
        interactor.getPost(postId, new IPostDetailInteractor.IPostDetailCallBack(){
            @Override
            public void onSuccess(Post post) {
                if(isViewAttached()){
                    view.getPostSuccess(post);
                    view.hideProgressBar();
                }
            }

            @Override
            public void onError(String errorMsg) {
                if(isViewAttached()) {
                    view.showError(errorMsg);
                    view.hideProgressBar();
                }
            }
        });

    }
}
