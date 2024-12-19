
export class Loans {

  loanNumber: number;
  customerId: number;
  startDt: Date;
  loanType: string;
  totalLoan: number;
  amountPaid: number;
  outstandingAmount: number;

  constructor(loanNumber?: number, customerId?: number, startDt?: Date, loanType?: string, totalLoan?: number, amountPaid?: number, outstandingAmount?: number){
    this.loanNumber = loanNumber ?? 0;
    this.customerId = customerId ?? 0;
    this.startDt = startDt!;
    this.loanType = loanType ?? '';
    this.totalLoan = totalLoan ?? 0;
    this.amountPaid = amountPaid ?? 0;
    this.outstandingAmount = outstandingAmount ?? 0;
  }

}
