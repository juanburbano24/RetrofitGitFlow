package pe.edu.cibertec.retrofitgitflow.presentation.main.presenter;

import java.util.List;

import pe.edu.cibertec.retrofitgitflow.data.entities.Post;
import pe.edu.cibertec.retrofitgitflow.domain.mainInteractor.IMainInteractor;
import pe.edu.cibertec.retrofitgitflow.presentation.main.IMainContract;

public class MainPresenter implements IMainContract.IPresenter {
    IMainContract.IView view;
    IMainInteractor interactor;

    public MainPresenter(IMainInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void attachView(IMainContract.IView view) {
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
    public void getAllPost() {
        view.showProgressBar();
        //Aca usaremos un interceptor de la clase Main
        interactor.getAllPost(new IMainInteractor.IMainCallBack() {
            @Override
            public void onSuccess(List<Post> postList) {
                System.out.println("IMainCallBack: onSuccess: " + postList.size());
                System.out.println("IMainCallBack: onSuccess: isViewAttached: " + isViewAttached());
                if(isViewAttached()){
                    view.getAllPostSuccess(postList);
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
