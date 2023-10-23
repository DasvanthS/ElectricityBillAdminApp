import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MonthlybillListComponent } from './monthlybill-list.component';

describe('MonthlybillListComponent', () => {
  let component: MonthlybillListComponent;
  let fixture: ComponentFixture<MonthlybillListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MonthlybillListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MonthlybillListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
