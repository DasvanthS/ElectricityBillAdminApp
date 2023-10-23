import { Customer } from "./customer";

export class MonthlyBill {
  id!: number;
  customer!: Customer;
  month!: string; 
  dueDate!: string;  
  unitConsumption!: number;
  amount!: number;
  onlinePayDisAmt!: number;
  dueDateDisAmt!: number;
  paid!: boolean;
  }
  