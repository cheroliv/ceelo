import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IGame } from 'app/shared/model/game.model';
import { getEntities } from './game.reducer';

export const Game = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const gameList = useAppSelector(state => state.game.entities);
  const loading = useAppSelector(state => state.game.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  return (
    <div>
      <h2 id="game-heading" data-cy="GameHeading">
        <Translate contentKey="ceeloApp.game.home.title">Games</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="ceeloApp.game.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/game/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="ceeloApp.game.home.createLabel">Create new Game</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {gameList && gameList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="ceeloApp.game.id">Id</Translate>
                </th>
                <th>
                  <Translate contentKey="ceeloApp.game.winnerPlayerId">Winner Player Id</Translate>
                </th>
                <th>
                  <Translate contentKey="ceeloApp.game.date">Date</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {gameList.map((game, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/game/${game.id}`} color="link" size="sm">
                      {game.id}
                    </Button>
                  </td>
                  <td>{game.winnerPlayerId}</td>
                  <td>{game.date ? <TextFormat type="date" value={game.date} format={APP_LOCAL_DATE_FORMAT} /> : null}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/game/${game.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/game/${game.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/game/${game.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="ceeloApp.game.home.notFound">No Games found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default Game;
