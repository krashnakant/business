import {Component, OnInit} from '@angular/core';
import {MatDialogRef} from '@angular/material/dialog';
import {Agency} from "../../models/agency";
import {AgencyService} from "../../services/agency.service";
import {ICountry, ICurrency} from "../../models/SelectUtil";

@Component({
  selector: 'app-new-employee-dialog',
  templateUrl: './new-agency-dialog.component.html',
  styleUrls: ['./new-agency-dialog.component.scss']
})
export class NewAgencyDialogComponent implements OnInit {

  agency: Agency;

  currencyCode: ICurrency[] = [
    {value: 'GBP', viewValue: 'GBP'},
    {value: 'EUR', viewValue: 'EUR'},
    {value: 'USD', viewValue: 'USD'}
  ]

  countryCode: ICountry[] = [
    {value: 'FRA', viewValue: 'FRA'},
    {value: 'GBR', viewValue: 'FRA'},
    {value: 'USA', viewValue: 'USA'},
    {value: 'DEU', viewValue: 'DEU'},
    {value: 'ITA', viewValue: 'ITA'}
  ]

  constructor(private dialogRef: MatDialogRef<NewAgencyDialogComponent>,
              private agencyService: AgencyService,
  ) {
  }


  ngOnInit() {
    this.agency = new Agency();
  }

  onSave() {
    this.agencyService.addAgency(this.agency);
    this.dialogRef.close(null);
  }


  onCancel() {
    this.dialogRef.close(null);
  }
}
