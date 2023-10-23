import { MonthlyBill } from "./monthlyBill";

export class Customer {
  customerId!: number;
  name!: string;
  email!: string;
  phoneNumber!: number;
  monthlyBills!: MonthlyBill[]; 
}
  