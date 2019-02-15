package com.example.MoodVerse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Array;
import java.util.ArrayList;

@Controller
public class moodController {

    @Autowired
    private MovieRepository repository;
    @Autowired
    private UserRepo userRepo;


// THIS IS FOR LOGIN PAGE WHERE USERS VERIFICATION IS TAKING PLACE
    @GetMapping("/login")
    public String loginUser(HttpSession session){
       //initate a guest user.
        return "Login";
    }

    @PostMapping("/login")
    public String verifyUser(HttpSession session, @RequestParam String userName, @RequestParam String passWord){
        User inUser = new User(userName,passWord);
        System.out.println("In post "+inUser.getUserName()+" "+inUser.getPassword());

        User CurrentUser = userRepo.verification(inUser);

        if(CurrentUser == null){
            return "login";
        }

        System.out.println("this is current user: "+CurrentUser.getUserName()+" "+CurrentUser.getPassword());
        if(CurrentUser.getUserName() == "Guest"){
            System.out.println("nahen milla!");
            session.setAttribute("user",CurrentUser); // add Guest user to the session by default
            return "Home";
        }
        else
        {
            System.out.println("Mill gaya");
            session.setAttribute("user",CurrentUser);//add logged in user to the session
            return "Home";

        }

    }


// THIS IS FOR HOME PAGE WHERE USER (GUEST OR LOGGED IN USER) CAN INPUT THERE COLOR/MOOD INFO
    @GetMapping("/colorCheck")
    public String startSession(HttpSession session){
   //       session.setAttribute("users", users);
        return "Home";
    }

    @PostMapping("/colorCheck")
    public String duringSession(HttpSession session, @RequestParam Color color){
       User userData = (User)session.getAttribute("user");
       if(userData == null){
           return "login";
       }
        userData.setMoodHistory(color);
        System.out.println("user logged in : "+userData.getUserName());
        //Movie userData;
        ArrayList<Movie> resultMovie = new ArrayList<>();
        for(int i=0; i<repository.size(); i++){
           if(repository.getMovie(i).getColor()==color){
               resultMovie.add(repository.getMovie(i));
               System.out.println("found a match: "+repository.getMovie(i).getTitle());


           }
        }
        session.setAttribute("user",userData);
        session.setAttribute("MovieArray",resultMovie);
        return "Results";
    }

    //THIS IS FOR LOGGING OUT FROM THE WEBSITE AND GOTO LOGIN:
    @GetMapping("/logOut")
    public String logOut(HttpSession session){
        session.removeAttribute("user");         //remove the user
        session.removeAttribute("MovieArray");   //remove the data

        session.invalidate();

        return "login";
    }

    @GetMapping("/Profile")
    public String gotoProfile(){
        return "Profile";
    }

    @GetMapping("/About")
    public String gotoPresentation(){
        return "Presentation";
    }

}
