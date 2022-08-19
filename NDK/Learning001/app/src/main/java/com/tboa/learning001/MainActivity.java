package com.tboa.learning001;

import static com.tboa.learning001.SignatureUtils.signatureVerify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.tboa.learning001.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Example of a call to a native method
        TextView tv = binding.sampleText;
        // 参数的加密，应该是在 运行时架构中 HttpUtils ：1.对参数字典排序 HashMap，2.生成等加密链接 userName=240336124&userPwd=123456

//        PackageInfo packageInfo= null;
//        try {
//            packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
//        Signature[] signatures=packageInfo.signatures;
//        Log.d("myapp", signatures[0].toCharsString());
        signatureVerify(getApplicationContext());

        // 作为参数传给服务器，服务器也会对参数生成同样的密文，然后对加密的字符串进行比较
        tv.setText(SignatureUtils.signatureParams("userName=240336124&userPwd=123456"));
    }

}