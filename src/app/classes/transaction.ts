import { Customer } from "./customer";
import { MonthlyBill } from "./monthlyBill";
import { PaymentMethod } from "./payment-method";

export class Transaction {
    id!: number;
    monthlyBill!: MonthlyBill;
    customer!: Customer;
    paymentDate!: string; 
    amount!: number;
    paymentMethod!: PaymentMethod;
}
