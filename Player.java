public class Player extends Person {
    
    private Long chips;
    private Boolean playing;


    public Long getChips() {
        return chips;
    }
    public void setChips(Long chips) {
        this.chips = chips;
    }
    public Boolean getPlaying() {
        return playing;
    }
    public void setPlaying(Boolean playing) {
        this.playing = playing;
    }
    public Player(String firstName, Long chips, Boolean playing) {
        super(firstName);
        this.chips = chips;
        this.playing = playing;
    }
    public Player(Long chips, Boolean playing) {
        this.chips = chips;
        this.playing = playing;
    }
    
    
    public Player() {
    }
    
}
