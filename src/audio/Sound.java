package audio;

import org.lwjgl.openal.AL10;

import java.nio.IntBuffer;
import java.nio.ShortBuffer;

import static org.lwjgl.openal.AL10.alBufferData;
import static org.lwjgl.openal.AL10.alSourcei;
import static org.lwjgl.stb.STBVorbis.stb_vorbis_decode_filename;
import static org.lwjgl.system.MemoryStack.stackPop;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.libc.LibCStdlib.free;

public class Sound {

    private int bufferID;
    private static int sourceID;
    private String filePath;
    public static boolean isPlaying;

    public Sound(String filePath, boolean looping, float gain) {
        //if i'm not pooping i'm looping
        this.filePath = filePath;
        stackPush();
        IntBuffer channelsBuffer = stackPush().mallocInt(1);
        stackPush();
        IntBuffer sampleRateBuffer = stackPush().mallocInt(1);

        ShortBuffer rawAudioBuffer = stb_vorbis_decode_filename(filePath, channelsBuffer, sampleRateBuffer);

        if(rawAudioBuffer == null) {
            System.out.println("uh oh looks like it was poopin not loopin (cant find file)");
            stackPop();
            stackPop();
            return;
        }

        int channels = channelsBuffer.get();
        int sampleRate = sampleRateBuffer.get();
        stackPop();
        stackPop();

        int format = -1;
        if(channels == 1) {
            format = AL10.AL_FORMAT_MONO16;
        } else if(channels == 2) {
            format = AL10.AL_FORMAT_STEREO16;
        }

        bufferID = AL10.alGenBuffers();
        AL10.alBufferData(bufferID, format, rawAudioBuffer, sampleRate);

        sourceID = AL10.alGenSources();
        AL10.alSourcei(sourceID, AL10.AL_BUFFER, bufferID);
        AL10.alSourcei(sourceID, AL10.AL_LOOPING, looping ? AL10.AL_TRUE : AL10.AL_FALSE);
        AL10.alSourcei(sourceID, AL10.AL_POSITION, 0);
        AL10.alSourcef(sourceID, AL10.AL_GAIN, gain);

        free(rawAudioBuffer);

    }

    public void dispose() {
        AL10.alDeleteSources(sourceID);
        AL10.alDeleteBuffers(bufferID);
    }

    public void play() {
        int state = AL10.alGetSourcei(sourceID, AL10.AL_SOURCE_STATE);
        if(state == AL10.AL_STOPPED) {
            isPlaying = false;
            alSourcei(sourceID, AL10.AL_POSITION, 0);
        }

        if(!isPlaying) {
            AL10.alSourcePlay(sourceID);
            isPlaying = true;
        }

    }

    public static void stopAll() {
        AL10.alSourceStop(sourceID);
        isPlaying = false;
    }

    public void stop() {
        AL10.alSourceStop(sourceID);
        isPlaying = false;
    }

    public String getFilePath() {
        return filePath;
    }

    public boolean isPlaying() {
        int state = AL10.alGetSourcei(sourceID, AL10.AL_SOURCE_STATE);
        if (state == AL10.AL_STOPPED) {
            isPlaying = false;
        }
        return isPlaying;
    }
}

