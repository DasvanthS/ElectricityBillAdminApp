import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MonthlybillCreateComponent } from './monthlybill-create.component';

describe('MonthlybillCreateComponent', () => {
  let component: MonthlybillCreateComponent;
  let fixture: ComponentFixture<MonthlybillCreateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MonthlybillCreateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MonthlybillCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
