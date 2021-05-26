import {Component, OnInit, Output, EventEmitter} from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import {MatSnackBar, MatSnackBarRef, SimpleSnackBar} from '@angular/material/snack-bar'
import {NewAgencyDialogComponent} from '../new-agency-dialog/new-agency-dialog.component';
import {Router} from '@angular/router';

@Component({
  selector: 'app-toolbar',
  templateUrl: './toolbar.component.html',
  styleUrls: ['./toolbar.component.scss']
})
export class ToolbarComponent implements OnInit {

  @Output() toggleSidenav = new EventEmitter<void>();
  @Output() toggleDir = new EventEmitter<void>();

  constructor(private dialog: MatDialog,
              private snackBar: MatSnackBar,
              private router: Router) {
  }

  ngOnInit() {
  }

  openAgencyDialog() {
    let dialogRef = this.dialog.open(NewAgencyDialogComponent, {
      width: '450px'
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log('Dialog closed', result);

      if (result) {
        this.openSnackBar("Agency Added", "Navigate")
          .onAction().subscribe(() => {
          //navigate
          this.router.navigate(['/agency', result.id]);
        });
      }
    });
  }

  openSnackBar(message: string, action: string): MatSnackBarRef<SimpleSnackBar> {
    return this.snackBar.open(message, action, {
      duration: 2000,
    });
  }

}
