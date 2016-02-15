package orangeboat.sailorscurvy.Input;

import android.content.Context;
import android.media.MediaPlayer;

import orangeboat.sailorscurvy.Panels.GamePanel;
import orangeboat.sailorscurvy.R;

/**
 * Created by Jay on 1/30/2016.
 */
public class SFXLoader {
        MediaPlayer temp;
        Context context;
        GamePanel g;
        public SFXLoader(Context context, GamePanel gamePanel)
        {
            g = gamePanel;
            this.context = context;
            start();
        }
        public void start(){
            temp = MediaPlayer.create(context, R.raw.boatso);
            temp.setLooping(true);
            g.sfxLoad(temp);
            temp = MediaPlayer.create(context, R.raw.beeps);
            temp.setLooping(true);
            g.sfxLoad(temp);
            temp = MediaPlayer.create(context, R.raw.errgs);
            temp.setLooping(true);
            g.sfxLoad(temp);
        }
}
