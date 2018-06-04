package app.informaticajcper.com.demobdsqlite;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Modificar extends AppCompatActivity {
    EditText et_nombre,et_apellidos;
    Button bt_modificar,bt_borrar;
    int id;
    String nombre;
    String apellido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);
        Bundle b = getIntent().getExtras();
        if (b != null) {
            id = b.getInt("Id");
            nombre = b.getString("Nombre");
            apellido = b.getString("Apellido");
        }
        et_nombre = (EditText) findViewById(R.id.et_nombre);
        et_apellidos = (EditText) findViewById(R.id.et_apellidos);
        et_nombre.setText(nombre);
        et_apellidos.setText(apellido);
        bt_modificar = (Button) findViewById(R.id.bt_modificar);
        bt_borrar = (Button) findViewById(R.id.bt_borrar);
        bt_modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modificar(id,et_nombre.getText().toString(), et_apellidos.getText().toString());
                onBackPressed();

            }
        });
        bt_borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminar(id);
                onBackPressed();

            }
        });


    }

    private void modificar (int Id,String Nombre, String Apellido) {
        BaseHelper helper = new BaseHelper(this, "Demo", null, 1);
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql = "update Personas set Nombre='" + Nombre + "',Apellido='" + Apellido + "' where Id="+Id;
        db.execSQL(sql);
        db.close();

    }
    private void eliminar (int Id) {
        BaseHelper helper = new BaseHelper(this, "Demo", null, 1);
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql = "delete from Personas where Id="+Id;
        db.execSQL(sql);
        db.close();

    }


}
