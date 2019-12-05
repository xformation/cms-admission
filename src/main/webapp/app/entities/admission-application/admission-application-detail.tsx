import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './admission-application.reducer';
import { IAdmissionApplication } from 'app/shared/model/admission-application.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAdmissionApplicationDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class AdmissionApplicationDetail extends React.Component<IAdmissionApplicationDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { admissionApplicationEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            AdmissionApplication [<b>{admissionApplicationEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="sourceOfApplication">Source Of Application</span>
            </dt>
            <dd>{admissionApplicationEntity.sourceOfApplication}</dd>
            <dt>
              <span id="studentId">Student Id</span>
            </dt>
            <dd>{admissionApplicationEntity.studentId}</dd>
            <dt>
              <span id="applicationDate">Application Date</span>
            </dt>
            <dd>
              <TextFormat value={admissionApplicationEntity.applicationDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="completionDate">Completion Date</span>
            </dt>
            <dd>
              <TextFormat value={admissionApplicationEntity.completionDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="admissionNo">Admission No</span>
            </dt>
            <dd>{admissionApplicationEntity.admissionNo}</dd>
            <dt>
              <span id="admissionDate">Admission Date</span>
            </dt>
            <dd>
              <TextFormat value={admissionApplicationEntity.admissionDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="comments">Comments</span>
            </dt>
            <dd>{admissionApplicationEntity.comments}</dd>
            <dt>
              <span id="applicationStatus">Application Status</span>
            </dt>
            <dd>{admissionApplicationEntity.applicationStatus}</dd>
            <dt>
              <span id="branchId">Branch Id</span>
            </dt>
            <dd>{admissionApplicationEntity.branchId}</dd>
            <dt>
              <span id="createdBy">Created By</span>
            </dt>
            <dd>{admissionApplicationEntity.createdBy}</dd>
            <dt>
              <span id="createdOn">Created On</span>
            </dt>
            <dd>
              <TextFormat value={admissionApplicationEntity.createdOn} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="updatedBy">Updated By</span>
            </dt>
            <dd>{admissionApplicationEntity.updatedBy}</dd>
            <dt>
              <span id="updatedOn">Updated On</span>
            </dt>
            <dd>
              <TextFormat value={admissionApplicationEntity.updatedOn} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>Admission Enquiry</dt>
            <dd>{admissionApplicationEntity.admissionEnquiryId ? admissionApplicationEntity.admissionEnquiryId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/admission-application" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/admission-application/${admissionApplicationEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ admissionApplication }: IRootState) => ({
  admissionApplicationEntity: admissionApplication.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(AdmissionApplicationDetail);
