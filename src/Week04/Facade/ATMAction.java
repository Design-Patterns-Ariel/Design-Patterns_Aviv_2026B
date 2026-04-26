package Facade;

public abstract class ATMAction {


    public final void ValidateCard() {
        System.out.println("Checking if card is valid...");
    }
    public final void ValidatePincode() {
        System.out.println("Cheking if pincode is valid");
    }
    public final void ValidateAccount() {
        System.out.println("Checking if account is valid");
    }
    public final void ValidateBalance() {
        System.out.println("Checking Balance...");
    }
    public final void GiveClientHisMoney() {
        System.out.println("Giving client his money...");
    }

}
