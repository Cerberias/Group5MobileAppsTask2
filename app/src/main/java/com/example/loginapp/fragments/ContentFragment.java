package com.example.loginapp.fragments;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
//import android.support.v4.app.Fragment;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.loginapp.R;
import com.example.loginapp.model.Word;
import com.example.loginapp.adapters.WordAdapter;

import java.util.ArrayList;

/**
 * {@link Fragment} that displays a list of color vocabulary words.
 */
public class ContentFragment extends Fragment {

    private String fragmentType;
    /** Handles playback of all the sound files */
    private MediaPlayer mMediaPlayer;

    /** Handles audio focus when playing a sound file */
    private AudioManager mAudioManager;

    /**
     * This listener gets triggered whenever the audio focus changes
     * (i.e., we gain or lose audio focus because of another app or device).
     */
    private final AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                // our app is allowed to continue playing sound but at a lower volume. We'll treat
                // both cases the same way because our app is playing short sound files.

                // Pause playback and reset player to the start of the file. That way, we can
                // play the word from the beginning when we resume playback.
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                // Stop playback and clean up resources
                releaseMediaPlayer();
            }
        }
    };

    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed
     * playing the audio file.
     */
    private final MediaPlayer.OnCompletionListener mCompletionListener = mediaPlayer -> {
        // Now that the sound file has finished playing, release the media player resources.
        releaseMediaPlayer();
    };

    public ContentFragment(String type) {
        // Required empty public constructor
        fragmentType = type;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.word_list, container, false);

        // Create and setup the {@link AudioManager} to request audio focus
        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        // Create a list of words
        final ArrayList<Word> words = new ArrayList<>();

        WordAdapter adapter;

        if (fragmentType == "color"){
            words.add(new Word("red", "weṭeṭṭi", R.mipmap.color_red,R.raw.color_red));
            words.add(new Word("mustard yellow", "chiwiiṭә", R.mipmap.color_mustard_yellow,R.raw.color_mustard_yellow));
            words.add(new Word("dusty yellow", "ṭopiisә", R.mipmap.color_dusty_yellow,R.raw.color_dusty_yellow));
            words.add(new Word("green", "chokokki", R.mipmap.color_green,R.raw.color_green));
            words.add(new Word("brown", "ṭakaakki", R.mipmap.color_brown,R.raw.color_brown));
            words.add(new Word("gray", "ṭopoppi", R.mipmap.color_gray,R.raw.color_gray));
            words.add(new Word("black", "kululli", R.mipmap.color_black,R.raw.color_black));
            words.add(new Word("white", "kelelli", R.mipmap.color_white,R.raw.color_white));

            // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
            // adapter knows how to create list items for each item in the list.
            adapter = new WordAdapter(getActivity(), words, R.color.category_colors);
        }else if (fragmentType == "family"){
            words.add(new Word("father", "әpә", R.mipmap.family_father,R.raw.family_father));
            words.add(new Word("mother", "әṭa", R.mipmap.family_mother,R.raw.family_mother));
            words.add(new Word("son", "angsi", R.mipmap.family_son,R.raw.family_son));
            words.add(new Word("daughter", "tune", R.mipmap.family_daughter,R.raw.family_daughter));
            words.add(new Word("older brother", "taachi", R.mipmap.family_older_brother,R.raw.family_older_brother));
            words.add(new Word("younger brother", "chalitti", R.mipmap.family_younger_brother,R.raw.family_younger_brother));
            words.add(new Word("older sister", "teṭe", R.mipmap.family_older_sister,R.raw.family_younger_sister));
            words.add(new Word("younger sister", "kolliti", R.mipmap.family_younger_sister,R.raw.family_younger_sister));
            words.add(new Word("grandmother ", "ama", R.mipmap.family_grandmother,R.raw.family_grandmother));
            words.add(new Word("grandfather", "paapa", R.mipmap.family_grandfather,R.raw.family_grandfather));

            // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
            // adapter knows how to create list items for each item in the list.
            adapter = new WordAdapter(getActivity(), words, R.color.category_family);
        }else if (fragmentType == "phrases"){
            words.add(new Word("Where are you going?", "minto wuksus",R.raw.phrase_where_are_you_going));
            words.add(new Word("What is your name?", "tinnә oyaase'nә",R.raw.phrase_what_is_your_name));
            words.add(new Word("My name is...", "oyaaset...",R.raw.phrase_my_name_is));
            words.add(new Word("How are you feeling?", "michәksәs?",R.raw.phrase_how_are_you_feeling));
            words.add(new Word("I’m feeling good.", "kuchi achit",R.raw.phrase_im_feeling_good));
            words.add(new Word("Are you coming?", "әәnәs'aa?",R.raw.phrase_are_you_coming));
            words.add(new Word("Yes, I’m coming.", "hәә’ әәnәm",R.raw.phrase_yes_im_coming));
            words.add(new Word("I’m coming.", "әәnәm",R.raw.phrase_im_coming));
            words.add(new Word("Let’s go.", "yoowutis",R.raw.phrase_lets_go));
            words.add(new Word("Come here.", "әnni'nem",R.raw.phrase_come_here));

            // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
            // adapter knows how to create list items for each item in the list.
            adapter = new WordAdapter(getActivity(), words, R.color.category_phrases);
        }else{
            words.add(new Word("one", "lutti", R.mipmap.number_one, R.raw.number_one));
            words.add(new Word("two", "otiiko", R.mipmap.number_two, R.raw.number_two));
            words.add(new Word("three", "tolooKosu", R.mipmap.number_three, R.raw.number_three));
            words.add(new Word("four", "oyyisa", R.mipmap.number_four, R.raw.number_four));
            words.add(new Word("five", "masokka", R.mipmap.number_five, R.raw.number_five));
            words.add(new Word("six", "temmoka", R.mipmap.number_six, R.raw.number_six));
            words.add(new Word("seven", "kenekaku", R.mipmap.number_seven, R.raw.number_seven));
            words.add(new Word("eight", "kawinta", R.mipmap.number_eight, R.raw.number_eight));
            words.add(new Word("nine", "wo'e", R.mipmap.number_nine, R.raw.number_nine));
            words.add(new Word("ten", "na'accha", R.mipmap.number_ten, R.raw.number_ten));

            // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
            // adapter knows how to create list items for each item in the list.
            adapter = new WordAdapter(getActivity(), words, R.color.category_numbers);
        }

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml layout file.
        ListView listView =  rootView.findViewById(R.id.list);

        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.
        listView.setAdapter(adapter);

        // Set a click listener to play the audio when the list item is clicked on
        listView.setOnItemClickListener((adapterView, view, position, l) -> {
            // Release the media player if it currently exists because we are about to
            // play a different sound file
            releaseMediaPlayer();

            // Get the {@link Word} object at the given position the user clicked on
            Word word = words.get(position);

            // Request audio focus so in order to play the audio file. The app needs to play a
            // short audio file, so we will request audio focus with a short amount of time
            // with AUDIOFOCUS_GAIN_TRANSIENT.
            int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                    AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

            if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                // We have audio focus now.

                // Create and setup the {@link MediaPlayer} for the audio resource associated
                // with the current word
                mMediaPlayer = MediaPlayer.create(getActivity(), word.getmAudioResourceId());

                // Start the audio file
                mMediaPlayer.start();

                // Setup a listener on the media player, so that we can stop and release the
                // media player once the sound has finished playing.
                mMediaPlayer.setOnCompletionListener(mCompletionListener);
            }
        });

        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();

        // When the activity is stopped, release the media player resources because we won't
        // be playing any more sounds.
        releaseMediaPlayer();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;

            // Regardless of whether or not we were granted audio focus, abandon it. This also
            // unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}