package com.example.quizmundoverde;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Usuario extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;

    private ImageView Photo;
    Button finalizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio_usuario);

        Photo = findViewById(R.id.btn_selected_photo);
        finalizar = findViewById(R.id.finalizar);

        finalizar.setOnClickListener(view -> {
            Intent in = new Intent(Usuario.this, Home.class);
            startActivity(in);
        });
        Photo.setOnClickListener(this::selecionarImagem);

    }

    /** @noinspection deprecation*/
    public void selecionarImagem(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                Photo.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
