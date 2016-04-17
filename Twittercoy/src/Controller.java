
import Twitter.TwitterModel;
import twitter4j.*;
import View.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Moncel
 */
public class Controller implements ActionListener{
    private TwitterModel model;
    private View view;
    private FollowUser follow;
    
    public void actionPerformed(ActionEvent ae) {
        
        Object source = ae.getSource();
        
        HomeTimeline home = (HomeTimeline) view;
        if (source.equals(home.getBtnCompose())){
            goToComposeTweet();
            home.dispose();
        }else if (source.equals(home.getBtnFollow())){
            goToComposeTweet();
            home.dispose();
        }else if (source.equals(home.getBtnRefresh())){
            String timeline = model.getHomeTimeline();
            home.setTimeline(timeline);
        }else if (source.equals(home.getBtnExit())){
            JOptionPane.showMessageDialog(null,"Thank you");
            System.exit(0);
        }
        else if(source.equals(home.getBtnCompose())){
        ComposeTweet compose = (ComposeTweet) view;
        if (source.equals(compose.getBtnCancel())){
            goToHomeTimeline();
            compose.dispose();
        } else if (source.equals(compose.getBtnTweet())){
            String tweet = compose.getTweet();
            model.tweetStatus(tweet);
            goToHomeTimeline();
            compose.dispose();
        }
        }
        
        if (source.equals(follow.getBtnCancel())){
            goToHomeTimeline();
            follow.dispose();
        } else if(source.equals(follow.getBtnFollow())){
            String user = follow.getUser();
            model.followUser(user);
            follow.setUser("");
        }
    }
    
    public Controller(){
        model = new TwitterModel();
        goToHomeTimeline();
        
    }
    
    public void goToHomeTimeline(){
        HomeTimeline home = new HomeTimeline();
        home.setVisible(true);
        home.addListener(this);
        home.setTimeline(model.getHomeTimeline());
        view = home;
    }
    
    public void goToComposeTweet(){
        ComposeTweet compose = new ComposeTweet();
        compose.setVisible(true);
        compose.addListener(this);
        view = compose;
    }
    
    public void goToFollowUser(){
        FollowUser follow = new FollowUser();
        follow.setVisible(true);
        follow.addListener(this);
        view = follow;
    }
    
    
}
 