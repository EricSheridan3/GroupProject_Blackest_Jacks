public class Card{
 private int rank;
 private String type;

  public Card(int rank,String type){
    this.rank=rank;
    this.type=type;
  }

  public int getRank(){
    return rank;
  }

  public void setRank(int rank){
    this.rank=rank;
  }
  public String getType(){
    return type;
  }
  public void setType(String type){
    this.type=type;
  }
  @Override
  public String toString() {
      return rank + " of " + type;
  }
  
}
