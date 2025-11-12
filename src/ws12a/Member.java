package ws12a;

public class Member {
    public final String name;
    public final int id;
    public final ClubMemberStatus status;
    public final int totalpay;
    public final int balance;

    public Member(String name, int id, ClubMemberStatus status, int totalpay,int balance){
        this.name = name;
        this.id=id;
        this.status=status;
        this.totalpay = totalpay;
        this.balance = balance;
    }

}
