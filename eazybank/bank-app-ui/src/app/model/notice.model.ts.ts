
export class Notice {

	public noticeSummary: string;
	public noticeDetails: string;
	public noticBegDt: Date;
	public noticEndDt: Date;

  constructor(noticeSummary?: string, noticeDetails?: string, noticBegDt?: Date, noticEndDt?: Date){
    this.noticeSummary = noticeSummary || "";
    this.noticeDetails = noticeDetails || "";
    this.noticBegDt = noticBegDt!;
    this.noticEndDt = noticEndDt!;
  }

}
