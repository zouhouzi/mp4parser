package com.googlecode.mp4parser.authoring.tracks;

import com.coremedia.iso.IsoFile;
import com.googlecode.mp4parser.authoring.Movie;
import com.googlecode.mp4parser.authoring.Track;
import com.googlecode.mp4parser.authoring.builder.DefaultMp4Builder;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.nio.channels.Channels;
import java.util.Arrays;

public class AC3TrackImplTest {
    @Test
    public void freeze() throws IOException {
        Track t = new AC3TrackImpl(new BufferedInputStream(AC3TrackImpl.class.getResourceAsStream("/com/googlecode/mp4parser/authoring/tracks/ac3-sample.ac3")));
        Movie m = new Movie();
        m.addTrack(t);

        DefaultMp4Builder mp4Builder = new DefaultMp4Builder();
        IsoFile isoFile = mp4Builder.build(m);
        IsoFile isoFileReference = new IsoFile(Channels.newChannel(AACTrackImplTest.class.getResourceAsStream("/com/googlecode/mp4parser/authoring/tracks/ac3-sample.mp4")));
        BoxComparator.check(isoFile, isoFileReference, Arrays.asList("/moov[0]/mvhd[0]", "/moov[0]/trak[0]/tkhd[0]", "/moov[0]/trak[0]/mdia[0]/mdhd[0]"));
    }
}
