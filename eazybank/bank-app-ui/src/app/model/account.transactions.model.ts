
export class AccountTransactions {

  accountNumber: number;
  customerId: number;
  transactionDt: Date;
  transactionSummary: string;
  transactionType: string;
  transactionAmt: number;
  closingBalance: number;

  constructor(accountNumber?: number, customerId?: number, transactionDt?: Date, transactionSummary?: string, transactionType?: string, transactionAmt?: number, closingBalance?: number){
    this.accountNumber = accountNumber ?? 0;
    this.customerId = customerId ?? 0;
    this.transactionDt = transactionDt!;
    this.transactionSummary = transactionSummary ?? '';
    this.transactionType = transactionType ?? '';
    this.transactionAmt = transactionAmt ?? 0;
    this.closingBalance = closingBalance ?? 0;
  }

}
