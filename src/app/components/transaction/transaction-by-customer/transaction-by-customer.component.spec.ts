import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TransactionByCustomerComponent } from './transaction-by-customer.component';

describe('TransactionByCustomerComponent', () => {
  let component: TransactionByCustomerComponent;
  let fixture: ComponentFixture<TransactionByCustomerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TransactionByCustomerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TransactionByCustomerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
