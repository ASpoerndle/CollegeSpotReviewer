package edu.elon.NearU.models;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


/*
1. ImageIO (image loading and saving)

 */


public class Collage {
    private final List<Picture> pictures = new ArrayList<>();

    public List<Picture> getPictures() {
        return pictures;
    }

    public void uploadPictures(List<InputStream> uploads) throws IOException {
        for (InputStream upload : uploads) {
            Picture picture = new Picture();
            picture.processNewPngImageUpload(upload);
            pictures.add(picture);
        }
    }
}
