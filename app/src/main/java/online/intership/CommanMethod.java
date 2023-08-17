package online.intership;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class CommanMethod {

    CommanMethod(Context context,String message){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();

    }
    CommanMethod(View view, String message) {
        Snackbar.make(view, message,Toast.LENGTH_SHORT).show();
    }
    CommanMethod(Context context,Class<?> nextClass){
        Intent intent=new Intent(context,nextClass);
        context.startActivity(intent);
    }
}
