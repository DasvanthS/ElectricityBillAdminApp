import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateCashComponent } from './update-cash.component';

describe('UpdateCashComponent', () => {
  let component: UpdateCashComponent;
  let fixture: ComponentFixture<UpdateCashComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateCashComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateCashComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
