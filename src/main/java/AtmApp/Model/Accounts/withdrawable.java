package AtmApp.Model.Accounts;

import AtmApp.Model.Exceptions.reachedLimitException;

interface withdrawable {
    /*void withdraw(Cash cash, int[] bills) throws NotEnoughBillsException, maximumWithdrawException,
            negativeBalanceException, reachedLimitException;*/

    void withdraw(double amount) throws reachedLimitException;
}