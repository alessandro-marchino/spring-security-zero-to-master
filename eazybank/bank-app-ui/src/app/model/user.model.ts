
export class User{

  customerId: number;
  name: string;
  mobileNumber: string;
  email : string;
  password: string;
  role : string;
  statusCd: string;
  statusMsg : string;
  authStatus : string;

  constructor(customerId?: number, name?: string, mobileNumber?: string, email?: string, password?: string, role?: string, statusCd?: string, statusMsg?: string, authStatus?: string){
    this.customerId = customerId ?? 0;
    this.name = name ?? '';
    this.mobileNumber = mobileNumber ?? '';
    this.email = email ?? '';
    this.password = password ?? '';
    this.role = role ?? '';
    this.statusCd = statusCd ?? '';
    this.statusMsg = statusMsg ?? '';
    this.authStatus = authStatus ?? '';
  }

}
