import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AtencionClienteModule} from './atencion-cliente/atencion-cliente.module';
import {ReportesModule} from './reportes/reportes.module';

const routes: Routes = [
  {
  path: 'registrar',
  loadChildren: () => import('./atencion-cliente/atencion-cliente.module').then(m => m.AtencionClienteModule), // Lazy load
  data: { preload: true }
  },
  {
    path: 'calcular',
    loadChildren: () => import('./reportes/reportes.module').then(m => m.ReportesModule), // Lazy load
    data: { preload: true }
    }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
