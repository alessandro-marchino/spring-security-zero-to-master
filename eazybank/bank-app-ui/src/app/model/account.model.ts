
export class Account {

  customerId: number;
  accountNumber: number;
  accountType: string;
  branchAddress: string;

  constructor(customerId?: number, accountNumber?: number, accountType?: string, branchAddress?: string){
    this.customerId = customerId ?? 0;
    this.accountNumber = accountNumber ?? 0;
    this.accountType = accountType ?? '';
    this.branchAddress = branchAddress ?? '';
  }

}
