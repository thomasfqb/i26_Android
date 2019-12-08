package fr.utt.if26.project.if26_android.Services;

public interface ResultHandler<T> {
    void onSuccess(T data);
    void onFailure(Exception e);
}

