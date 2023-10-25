package dev.mobile.medicalink

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

class AddTraitements : AppCompatActivity() {
    private lateinit var photoButton: LinearLayout
    private lateinit var loadButton: LinearLayout
    private lateinit var manualImportButton: LinearLayout

    private lateinit var annuler : ImageView

    private var currentPhotoPath: Uri? = null

    private lateinit var photoLauncher: ActivityResultLauncher<Uri>
    private lateinit var loadLauncher: ActivityResultLauncher<String>
    private lateinit var addManuallyLauncher: ActivityResultLauncher<Intent>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_traitements)


        photoButton = findViewById(R.id.cardphoto)
        loadButton = findViewById(R.id.cardload)
        manualImportButton = findViewById(R.id.cardaddmanually)

        manualImportButton = findViewById(R.id.cardaddmanually)
        annuler = findViewById<ImageView>(R.id.annulerAddTraitement)

        photoLauncher = registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
            // Utilise le chemin de l'image capturée (currentPhotoPath)
            if (currentPhotoPath != null) {
                // L'image a été capturée avec succès, tu peux utiliser currentPhotoPath ici
                // Ensuite, lance une autre activité pour afficher l'image ou effectuer d'autres actions
                startActivity(Intent(this, PreviewActivity::class.java)
                    .putExtra("uri", currentPhotoPath.toString())
                    .putExtra("type", "photo"))
            } else {
                null
            }
        }

        loadLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            if (uri != null) {
                startActivity(Intent(this, PreviewActivity::class.java)
                    .putExtra("uri", uri.toString())
                    .putExtra("type", "charger"))
            }else{
                null
            }
        }

        addManuallyLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // Gérez l'activité de résultat ici
            }
        }




        photoButton.setOnClickListener {
            val uri: Uri = createImageFile()
            currentPhotoPath = uri
            Log.d("MedicalinkBug", uri.toString())
            photoLauncher.launch(uri)
        }

        loadButton.setOnClickListener {
            loadLauncher.launch("image/*")
        }

        manualImportButton.setOnClickListener {
            val intent = Intent(this, AjoutManuelSearch::class.java)
            addManuallyLauncher.launch(intent)
        }


        annuler.setOnClickListener {
            finish()
        }




    }

    private fun createImageFile(): Uri {
        val provider: String = "${applicationContext.packageName}.fileprovider"
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName = "JPEG_" + timeStamp + "_"

        val image = File.createTempFile(
            imageFileName, /* prefix */
            ".jpg", /* suffix */
            cacheDir      /* directory */
        ).apply {
            createNewFile()
        }

        return FileProvider.getUriForFile(applicationContext, provider, image)
    }
}
