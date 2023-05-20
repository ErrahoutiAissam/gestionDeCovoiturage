package com.example.gestionDeCovoiturage.service;

import com.example.gestionDeCovoiturage.utils.NameGenerator;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class FirebaseStorageService {
   private final Storage firebaseStorage;
   private final String bucketName;

   @Autowired
   public FirebaseStorageService(Storage firebaseStorage, @Value("${firebase.bucket}") String bucketName) {
      this.firebaseStorage = firebaseStorage;
      this.bucketName = bucketName;
   }

   public String uploadImage(MultipartFile file, String fileName) throws IOException {
      BlobId blobId = BlobId.of(bucketName, fileName);
      BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(file.getContentType()).build();
      firebaseStorage.create(blobInfo, file.getBytes());
      return getDownloadUrl(blobId);
   }

   public String updateImage(String fileName, MultipartFile file) throws IOException {
      BlobId blobId = BlobId.of(bucketName, fileName);
      BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(file.getContentType()).build();
      firebaseStorage.create(blobInfo, file.getBytes());
      return getDownloadUrl(blobId);
   }

   public void deleteImage(String fileName) {
      BlobId blobId = BlobId.of(bucketName, fileName);
      firebaseStorage.delete(blobId);
   }

   private String getDownloadUrl(BlobId blobId) {
      return firebaseStorage.signUrl(BlobInfo.newBuilder(blobId).build(), 1, TimeUnit.DAYS)
              .toString();
   }


}
