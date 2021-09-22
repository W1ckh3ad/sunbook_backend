package de.sunbook.api.models.responsemodels;

/*
This class defines the response for the user book map
*/
public class UserBookModelForBookResponseSingleModel {
    private String userDescription;
    private UserReponseForShopsModel user;
    private int bookId;
    public UserBookModelForBookResponseSingleModel() {
    }
    public UserBookModelForBookResponseSingleModel(String userDescription, UserReponseForShopsModel user, int bookId) {
        this.userDescription = userDescription;
        this.user = user;
        this.bookId = bookId;
    }
    public String getUserDescription() {
        return userDescription;
    }
                
    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription;
    }
    public UserReponseForShopsModel getUser() {
        return user;
    }
    public void setUser(UserReponseForShopsModel user) {
        this.user = user;
    }
    public int getBookId() {
        return bookId;
    }
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    
}
