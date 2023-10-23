import { TestBed } from '@angular/core/testing';

import { MonthlybillService } from './monthlybill.service';

describe('MonthlybillService', () => {
  let service: MonthlybillService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MonthlybillService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
