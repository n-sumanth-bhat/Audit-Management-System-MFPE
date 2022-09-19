import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SeverityComponent } from './severity.component';

describe('SeverityComponent', () => {
  let component: SeverityComponent;
  let fixture: ComponentFixture<SeverityComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SeverityComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SeverityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
